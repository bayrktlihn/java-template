package io.bayrktlihn.template.util;

import io.bayrktlihn.template.util.date.model.LocalDateFromTo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateFromToObject<T> {
    private LocalDateFromTo fromTo;
    private T object;


}
