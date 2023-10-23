

allprojects {

		ext {
            set("lc_version", "2.3.0")
            set("nav_version", "2.3.3")
        }
		// Add More
}


plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false

}