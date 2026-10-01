# user-service-testcontainers

Testing a Spring Data JPA repository against a real MySQL database instead of an in-memory one, using [Testcontainers](https://testcontainers.com).

`TestContainerExampleTest` starts a MySQL container, points Spring at it with `@DynamicPropertySource`, and runs the repository test against it. The container is thrown away when the test ends.

## Run the tests

You need Docker running.

```
./gradlew test
```
