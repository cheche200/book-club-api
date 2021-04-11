package com.ignite.bookclubapi.service;

import com.ignite.bookclubapi.domain.Member;
import com.ignite.bookclubapi.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public Optional<Member> findById(String id) {
        return repository.findById(id);
    }

    public void save(Member member) {
        repository.saveAndFlush(member);
    }
}
