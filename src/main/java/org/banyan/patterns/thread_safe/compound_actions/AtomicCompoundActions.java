package org.banyan.patterns.thread_safe.compound_actions;


import org.banyan.patterns.GuardedBy;
import org.banyan.patterns.ThreadSafe;

/**
 * Pattern: Atomic Compound Actions
 * <p>
 * Motivations: Compounded actions are actions that depends on a sequence of
 * events. They need to be executed atomically as a single unit which totally
 * fails or complete. check-then-act, read-modify-write and compare-and-swap are
 * common idioms in concurrent programming that can cause race conditions if not
 * treated right.
 * <p>
 * Intent: Prevent race condition issues while using intrinsic locking
 * mechanisms when executing compound actions; protect every path where the
 * involved variables are used.
 * <p>
 * Applicability: read-modify-write (i++ operator), check-then-act (lazy
 * initialization, singleton), compare-and-swap (Stacks).
 */
@ThreadSafe
public class AtomicCompoundActions {

    @GuardedBy("this")
    private Object value;

    // example
    public synchronized void checkThenAct() {
        if (value != null) { // check
            dependentAction(); // if true then-act
        }
    }

    public void dependentAction() {
    }

    public synchronized void getValue() {

    }

}
