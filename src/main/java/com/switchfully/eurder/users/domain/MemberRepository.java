package com.switchfully.eurder.users.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private HashMap<String, Member> memberHashMap = new HashMap<>();

    public Member addMember(Member member) {
        memberHashMap.put(member.getId(), member);
        return memberHashMap.get(member.getId());
    }

    public List<Member> getMembers() {
        return memberHashMap.values().stream().toList();
    }
}
