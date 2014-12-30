/**
 * Created by Hello on 2014/12/22.
 */
class Person implements Serializable {
    def name
    def age

    @Override
    String toString() {
        return "$name, $age"
    }

    @Override
    boolean equals(Object person) {
        return name == person.name && age == person.age
    }
}
