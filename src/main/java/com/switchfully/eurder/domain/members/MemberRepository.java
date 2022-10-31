package com.switchfully.eurder.domain.members;

import com.switchfully.eurder.security.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private HashMap<String, Member> memberHashMap;

    public MemberRepository() {
        this.memberHashMap = new HashMap<>();
        memberHashMap.put("admin", new Member("password", "admin", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
        memberHashMap.get("admin").setRole(Role.ADMIN);
        memberHashMap.put("memberToAdmin", new Member("password", "member", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
        memberHashMap.put("member", new Member("password", "member", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
    }

    public Member addMember(Member member) {
        memberHashMap.put(member.getId(), member);
        return memberHashMap.get(member.getId());
    }

    public List<Member> getMembers() {
        return memberHashMap.values().stream().toList();
    }

    public Member getMember(String id) {
        return memberHashMap.get(id);
    }
}
