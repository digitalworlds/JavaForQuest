plugins {
    alias(libs.plugins.android.application)
}

val natives by configurations.creating

android {
    namespace = "edu.ufl.j4q"
    compileSdk = 36

    defaultConfig {
        applicationId = "edu.ufl.j4q"
        minSdk = 32
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_15
        targetCompatibility = JavaVersion.VERSION_15
    }
        sourceSets {
                getByName("main") {
                    manifest.srcFile("src/main/AndroidManifest.xml")
                    java.srcDirs("src/main/java", "../../../java", "../../../javax")
                    assets.srcDirs("../../../assets")
                    res.srcDirs("src/main/res")
                }
        }
}


dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.ui.text)

    implementation(libs.jbullet)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)


}

