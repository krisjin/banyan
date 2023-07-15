package org.banyan.patterns.thread_safe.initialization;

/**
 * Pattern: Safe Lazy Initialization
 * <p>
 * Motivations: Due to the Java Memory Model, some lazy initialization patterns can be unsafe.
 * Apply the Lazy Initialization idiom correctly using the mechanisms provided
 * by the JVM.
 * <p>
 * Intent: Safe initialize our object using the holder idiom. Memory
 * writes made during static initialization are automatically visible to all
 * threads.
 * <p>
 * Applicability: Always when you want to initialize an object in a concurrent code
 * execution.
 */
public class SafeInitializationHolder {
    private static class ResourceHolder {
        public static Object resource = new Object();
    }

    public static Object getResource() {
        return ResourceHolder.resource;
    }
}
