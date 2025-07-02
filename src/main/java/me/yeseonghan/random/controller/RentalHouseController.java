package me.yeseonghan.random.controller;

import lombok.RequiredArgsConstructor;
import me.yeseonghan.random.service.RentalHouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RentalHouseController {
    private final RentalHouseService rentalHouseService;

    @GetMapping("/api/test")
    public ResponseEntity<String> testFetch(){
        String json = rentalHouseService.fetchAllDataFromApi();
        return ResponseEntity.ok(json);
    }
}
