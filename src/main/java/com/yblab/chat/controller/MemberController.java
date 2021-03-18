package com.yblab.chat.controller;

import com.yblab.chat.model.Member;
import com.yblab.chat.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members")
    public List<Member> members(){
        return memberRepository.findAll();
    }

    @GetMapping("/member/{id}")
    public Member member(@PathVariable Long id){
        return memberRepository.findById(id).orElse(null);
    }
    @PostMapping("/member")
    public Member ceateMember(@RequestBody Member newMember){
        return memberRepository.save(newMember);
    }
    @PutMapping("/member/{id}")
    public Member modifyMember(@RequestBody Member aMember, @PathVariable Long id){
        return memberRepository.findById(id)
                .map(member -> {
                    member.setName(aMember.getName());
                    member.setPassword(aMember.getPassword());
                    member.setPhone(aMember.getPhone());
                    member.setEmail(aMember.getEmail());
                    member.setBio(aMember.getBio());
                    return memberRepository.save(member);
                })
                .orElseGet( ()-> {
                    aMember.setId(id);
                    return memberRepository.save(aMember);
                });
    }

    @DeleteMapping("/member/{id}")
    public void deleteMember(@PathVariable Long id){
        memberRepository.deleteById(id);
    }
}
