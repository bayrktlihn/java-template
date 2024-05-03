package io.bayrktlihn.template.util.date.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class DateFromToObject<T> {
    private DateFromTo dateFromTo;
    private T object;

    public DateFromToObject(Date from, Date to, T object) {
        this.dateFromTo = new DateFromTo(from, to);
        this.object = object;
    }

    public Date getFrom() {
        return dateFromTo.getFrom();
    }

    public Date getTo() {
        return dateFromTo.getTo();
    }
}
