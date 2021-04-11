package com.ignite.bookclubapi.controller;

import com.ignite.bookclubapi.domain.Member;
import com.ignite.bookclubapi.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/member")
public class MemberController {

    private MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Member getById(@PathVariable String id){
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Member Id={} not found", id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Member member){
        service.save(member);
    }

}
