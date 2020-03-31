package ch.fhnw.oop2.tasky.part1.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Die Zustände, welche eine Task haben kann.
 */
public enum Status {
    Todo, Doing, Done, Review;

    Status(){

    }

    public static List<Status> getAllStati(){
        return Stream.of(Status.Todo, Status.Doing, Status.Done)
                .collect(Collectors.toList());
    }

}
