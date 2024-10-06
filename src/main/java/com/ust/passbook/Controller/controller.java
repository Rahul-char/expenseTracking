package com.ust.passbook.Controller;


import com.ust.passbook.Service.service;
import com.ust.passbook.dto.expensiveDto;
import com.ust.passbook.model.expensive;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ust")
public class controller {

    @Autowired
    private service service;

    @PostMapping("/expense")
    public ResponseEntity<expensive> debit(@RequestBody @Valid expensiveDto d){
        return new ResponseEntity<>(service.add(d), HttpStatus.CREATED);
    }

    @GetMapping("/totalSpend/{date}")
    public int totalSpent(@PathVariable("date") LocalDate d){
        return service.total(d);
    }

    @GetMapping("/groupSpend/{date}")
    public Map<String, Integer> groupSpend(@PathVariable("date") LocalDate d){
        return service.group(d);
    }

    @GetMapping("/monthSpend/{monthStartDate}")
    public int monthSpend(@PathVariable("monthStartDate") LocalDate monthStartDate){
        return service.monthSpend(monthStartDate);
    }

    @GetMapping("/balance")
    public int balance(){
        return service.getbal();
    }
}
