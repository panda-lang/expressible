# Expressible [![CI](https://github.com/panda-lang/expressible/actions/workflows/maven.yml/badge.svg)](https://github.com/panda-lang/expressible/actions/workflows/maven.yml) [![codecov](https://codecov.io/gh/panda-lang/expressible/branch/main/graph/badge.svg?token=LI1PAPD6NM)](https://codecov.io/gh/panda-lang/expressible)
Utility library, part of the panda-lang SDK, dedicated for functional codebases that require enhanced response handling.
Express yourself with inspired by Rust, Kotlin and Vavr wrappers, to provide better API using this tiny library.

<hr>

Supported wrappers (in `panda.std.*` package):
* `Result<Value, Error>` - solve error handling gracfully
* `Option<Value>` - enhanced alternative to standard `Optional<Value>
* `Lazy<Value>` - lazy values & runners
* `Completable<Value>` with `Publisher & Subscriber` pattern - synchronized alternative to `CompletableFuture<Value>`
* Simple reactive containers - `Reference<V>`, `MutableReference<V>`, `Computed`
* `Mono<A>`, `Pair<A, B>`, `Triple<A, B, C>`, `Quad<A, B, C, D>` - generic wrappers for set of values
* Throwing functions, runnables, suppliers and consumers - set of functional interfaces with support for exception signatures
* Tri and Quad consumers, functions and predicates - additional functional interfaces
* `PandaStream<Value>` - `Stream<Value>` wrapper with support for features provided by `expresible` library

<hr>

```xml
repositories {
    maven { url 'https://repo.panda-lang.org/releases' }
}

dependencies {
    implementation("org.panda-lang:expressible:1.1.0") // Core library
    implementation("org.panda-lang:expressible-kt:1.1.0") // Kotlin extensions
    implementation("org.panda-lang:expressible-kt-coroutines:1.1.0") // Kotlin coroutines extensions
    testImplementation("org.panda-lang:expressible-junit:1.1.0") // JUnit extensions
}
```

### Examples
Suggested snippets show only a small use-cases for the available api. 
You're not forced to use this library this way, so you may need to find your style in expressing your thoughts.
Adopting functional approach requires time and to simplify this process it's easier to slowly introduce new elements based on simple concepts.

#### Result

Rather than using `Exception` based error handling, return meaningful errors and interact with api responses gracefully.
Following functional programming patterns make sure your methods don't contain side effects and unexpected exit points. 

```kotlin
class UserFacade {
    // You can start adoption in a regular, non-functional codebases
    fun createUser(username: String): Result<User, String> {
        if (userRepository.findUserByName(username).isPresent()) {
            return error("User $username already exists")
        }
        return ok(userRepository.createUser(username))
    }
}

class UserEndpoint {
    // You can also use fully functional approach
    fun createUser(request: HttpRequest, response: HttpResponse) =
        userFacade.createUsername(request.param("username"))
            .peek { user -> response.respondWithJsonDto(user) }
            .onError { error -> ErrorReposne(BAD_REQUEST, error) }
}

internal class UserFacadeTest : UserSpec {
    // JUnit support
    @Test
    fun `should create user with a valid username` () {
        // given: a valid username
        val username = 'onlypanda'
        // when: user is created with the following name
        val user = userFacade.createUser(username)
        // then: user has been created
        assertOk(username, user.map(User::getUsername))
    }
} 
```

#### Option
Similar usage to `Optional<Value>` type provided by Java:

```java
Option<String> withValue = Option.of("Value");
Option<String> empty = Option.empty();
```

#### Lazy

```java
Lazy<String> completed = new Lazy<>("Value");
Lazy<String> lazy = new Lazy<>(() -> "Value");
Lazy<Void> initialize = Lazy.ofRunnable(() -> "Called just once);

String value = completed.get();
```

#### Completable

```java
Completable<String> completable = Completable.create();

completable
    .thenApply(value -> parseBoolean(value))
    .then(value -> System.out.println(value));

completable.complete("true");
```

#### Reactive

```java
Reference<Integer> a = reference(1);
MutableReference<Integer> b = mutableReference(2);
Computed<Integer> result = computed(dependencies(a, b), () -> a.get() + b.get());

result.subscribe(value -> System.out.println(value));
b.update(3); // prints "4"
```

#### Panda Stream

```java
PandaStream<String> empty = PandaStream.empty();
PandaStream<String> standard = PandaStream.of(new ArrayList<>().stream());
```

### Used by

* [Panda Organization](https://github.com/panda-lang) ([Panda](https://github.com/panda-lang/panda), [Hub](https://github.com/panda-lang/hub), [Light](https://github.com/panda-lang))
* [Reposilite](https://github.com/dzikoysk/reposilite)
* Libraries like [CDN](https://github.com/dzikoysk/cdn), [Dependency-Injector](https://github.com/dzikoysk/dependency-injector)
* [FunnyGuilds Organization](https://github.com/FunnyGuilds) ([FunnyGuilds](https://github.com/FunnyGuilds/FunnyGuilds), [FunnyCommands](https://github.com/FunnyGuilds/FunnyCommands))
* Private projects and API consumers of the given libraries
