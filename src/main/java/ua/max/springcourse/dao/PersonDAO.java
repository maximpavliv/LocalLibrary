package ua.max.springcourse.dao;

import org.springframework.stereotype.Component;
import ua.max.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Esteban", 27, "esteban@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Shantam", 28, "shantam@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Isma", 19, "isma@mail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Ester", 26, "ester@mail.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToUpdate = show(id);
        personToUpdate.setName(updatedPerson.getName());
        personToUpdate.setAge(updatedPerson.getAge());
        personToUpdate.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}