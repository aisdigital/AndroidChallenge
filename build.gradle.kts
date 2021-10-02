buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
