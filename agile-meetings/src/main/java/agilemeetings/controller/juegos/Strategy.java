package agilemeetings.controller.juegos;

import java.lang.annotation.*;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Strategy {
    Class<? extends JuegoHelper> type();
    JuegoEnum[] juegos() default {};
}