package io.bayrktlihn.template.util;

import io.bayrktlihn.template.util.date.model.LocalDateFromTo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateFromToObject<T> {
    private LocalDateFromTo fromTo;
    private T object;


    public static int compareWithLocalDateTo(LocalDateFromToObject o1, LocalDateFromToObject o2) {
        LocalDate to1 = o1.getFromTo().getTo() == null ? LocalDate.MAX : o1.getFromTo().getTo();
        LocalDate to2 = o2.getFromTo().getTo() == null ? LocalDate.MAX : o2.getFromTo().getTo();
        return to1.compareTo(to2);

    }


}
