# eclipseStoreTest

Run EclipseAccountAdapterTest using `mvn test` or from the IDE.

To compile to native image: `./mvnw -Pnative -DbuildArgs="-Ob" native:compile -DskipTests`

To run the native image: `./target/eclipse-store-test`
