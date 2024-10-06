package com.ust.passbook.Service;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.ust.passbook.Repository.repository;
import com.ust.passbook.dto.expensiveDto;
import com.ust.passbook.model.expensive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class service {

    @Autowired
    private repository r;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public service(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    public expensive add(expensiveDto d){
        expensive e=new expensive();
        e.setDrCr(d.getDrCr());
        e.setCreditedFrom(d.getCreditedFrom());
        e.setPaymentMode(d.getPaymentMode());
        e.setDataName(d.getDataName());
        e.setAmount(d.getAmount());
        e.setDate(d.getDate());
        return r.save(e);
    }

    public int total(LocalDate d) {
        return jpaStreamer.stream(expensive.class)
                .filter(i -> "Dr".equals(i.getDrCr()))
                .filter(expensive -> expensive.getDate().getDayOfMonth() == d.getDayOfMonth() && expensive.getDate().getYear() == d.getYear() && expensive.getDate().getMonth() == d.getMonth())
                .mapToInt(expensive::getAmount)
                .sum();
    }

    public Map<String, Integer> group(LocalDate d) {
        return jpaStreamer.stream(expensive.class)
                .filter(i -> "Dr".equals(i.getDrCr()))
                .filter(expensive -> expensive.getDate().getDayOfMonth() == d.getDayOfMonth() && expensive.getDate().getYear() == d.getYear() && expensive.getDate().getMonth() == d.getMonth())
                .collect(Collectors.groupingBy(expensive::getPaymentMode, Collectors.summingInt(expensive::getAmount)));
    }

    public int getbal() {
        int a = jpaStreamer.stream(expensive.class)
                .filter(i -> "Dr".equals(i.getDrCr()))
                .mapToInt(expensive::getAmount)
                .sum();
        int b = jpaStreamer.stream(expensive.class)
                .filter(i -> "Cr".equals(i.getDrCr()))
                .mapToInt(expensive::getAmount)
                .sum();
        return b-a;
    }


    public List<String> getDrCr() {
        return jpaStreamer.stream(expensive.class)

                .map(expensive::getDrCr)
                .toList();
    }


    public int monthSpend(LocalDate monthStartDate) {
        return jpaStreamer.stream(expensive.class)
                .filter(i -> "Dr".equals(i.getDrCr()))
                .filter(expensive -> expensive.getDate().getMonth() == monthStartDate.getMonth() && expensive.getDate().getYear() == monthStartDate.getYear())
                .mapToInt(expensive::getAmount)
                .sum();
    }
}
