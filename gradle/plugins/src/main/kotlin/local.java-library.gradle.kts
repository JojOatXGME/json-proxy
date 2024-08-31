plugins {
    id("local.base")
    id("java-library")
}

java {
    // Don't use Gradle's toolchain feature as it prevents building the project with more recent JDKs. Related issues:
    // https://github.com/gradle/gradle/issues/16256 - Ability to set a min language version for a toolchain
    // https://github.com/gradle/gradle/issues/17444 - Toolchains feature does not appear to treat Java as backwards compatible
    // https://github.com/gradle/gradle/issues/18894 - More flexibility in querying Java toolchains
    sourceCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}
