package com.concurrent.combination;

import com.concurrent.model.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过线程封闭机制来确保线程安全
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/9
 * Time: 22:09
 */
public class PersonSet {

    private final Set<Person> personSet = new HashSet<Person>();

    public synchronized void addPerson(Person person) {
        personSet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return personSet.contains(person);
    }

    public synchronized boolean removePerson(Person person) {
        return personSet.remove(person);
    }
}
