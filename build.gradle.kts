plugins {
    id("local.java-library")
}

dependencies {
    api(libs.jspecify)
    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5.jupiter)
    testRuntimeOnly(libs.junit5.engine)
}
