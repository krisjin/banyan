package org.banyan.features.java_memory_model;

/**
 * === Introduction ===
 * <p>
 * This will not include any Java concurrency feature, but a briefly explanation
 * on what is the Java Memory Model (JMM).
 * <p>
 * The Java Memory Model specifies the "minimal guarantees" on how about reads
 * and writes happens on shared variables. The JMM is an abstraction of the
 * multiples existing memory models in processor architectures.
 * <p>
 * == Reordering ==
 * <p>
 * When data is shared across threads, there's not so much guarantees on the
 * execution order by the threads, and because of the imprevisibility, it's
 * import to identify shared data and to use a proper synchronization mechanism
 * to ensure order and to keep the visibility guarantees provided by the JMM.
 * <p>
 * == Happens-Before ==
 * <p>
 * The JMM is defined in terms of actions, and some operations can have
 * "Happens-Before" relationship. In Happens-Before, if one action
 * "happens-before" another action, then everything done in the first action is
 * visible to the second.
 * <p>
 * In other terms, the write operations done by one thread are guaranteed to be
 * visible to a read coming from another thread if the write operation
 * "happens-before" the read operation.
 * <p>
 * Without the happen-before relationship, the JVM is free to reorder the
 * actions as it pleases.
 * <p>
 * == Happens-Before rules ==
 * <p>
 * Some structures have Happens-Before rules associated with it (text from JCP
 * book).
 * <p>
 * Program order rule: Actions in a thread "happens-before" every action in that
 * thread* that comes later in the program order. - "Keep the sequence of
 * instructions, if executed in the same thread".
 * <p>
 * Monitor lock/Explicit lock rule: An unlock on a lock "happens-before" every
 * subsequent lock on that same monitor lock. - "Locks only after it unlocks".
 * <p>
 * Volatile variable/Atomics rule: A write to a volatile or atomic field
 * "happens-before" every subsequent read of that same field. - "Writes
 * happens-before reads in volatile/atomic fields".
 * <p>
 * Thread start rule: A call to "Thread.start" in a thread "happens-before" than
 * every action on the started thread. - "Thread.start happens first than other
 * actions".
 * <p>
 * Thread termination rule: Any action in a thread "happens-before" any other
 * thread detects that thread has terminated, either by successfully return from
 * "Thread.join" or by "Thread.isAlive" returning false. - "Thread A will
 * execute everything before another Thread call .join on with."
 * <p>
 * Interruption rule: A thread calling interrupt on another thread
 * "happens-before" the interrupted thread detects the interrupt (either by
 * having InterruptedException thrown, or invoking isInterrupted or
 * interrupted).
 * <p>
 * Finalizer rule: The end of a constructor for an object "happens-before" the
 * start of the finalizer for that object.
 * <p>
 * Transitivity. If A happens-before B, and B happens-before C, then A
 * happens-before C.
 * <p>
 * Other rules from java library classes:
 * <p>
 * - Insert items in a thread-safe collection happens-before access or removal
 * of items;
 * <p>
 * - Actions in a thread happens-before than Runnable/Callable being sent to an
 * Executor;
 * <p>
 * - Actions taken by a asynchronous computation like Future happens-before
 * another threads calls Future.get;
 * <p>
 * - CountDownLatch.countDown happens-before a thread returns from await on this
 * latch;
 * <p>
 * - A Thread arriving at a CyclicBarrier happens-before the other threads are
 * released from that barrier exchange point or the barrier release action is
 * executed.
 */
public class WhatIsJavaMemoryModel {

}
