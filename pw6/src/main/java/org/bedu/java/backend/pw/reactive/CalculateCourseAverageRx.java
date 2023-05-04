package org.bedu.java.backend.pw.reactive;

import org.bedu.java.backend.pw.model.Course;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * @author Lenin Meza
 * @version 1.0.0
 */
@Component
public class CalculateCourseAverageRx {

    public Mono<Double> calculateAverage(Course course) {

        return Flux.fromStream(course.getScore().values().parallelStream())
                .collect(Collectors.averagingDouble(i -> (double) i));

    }
}
