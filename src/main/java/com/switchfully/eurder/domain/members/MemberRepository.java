package com.switchfully.eurder.domain.members;

import com.switchfully.eurder.security.Role;
import com.switchfully.eurder.service.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {
    private HashMap<String, Customer> memberHashMap;

    public MemberRepository() {
        this.memberHashMap = new HashMap<>();
        memberHashMap.put("admin", new Customer("password", "admin", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
        memberHashMap.get("admin").setRole(Role.ADMIN);
        memberHashMap.put("memberToAdmin", new Customer("password", "member", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
        memberHashMap.put("member", new Customer("password", "member", "Squarepants", "spongebob@hotmail.com", "555", new Address("1000", "waterstreet", "1", "5", "")));
    }

    public Customer addMember(Customer customer) {
        memberHashMap.put(customer.getId(), customer);
        return memberHashMap.get(customer.getId());
    }

    public List<Customer> getMembers() {
        return memberHashMap.values().stream().toList();
    }

    public Customer getMemberForSecurityCheck(String id) {
        return memberHashMap.get(id);
    }

    public Customer getMember(String id) {
        if(memberHashMap.get(id) == null) {
            throw new UserNotFoundException();
        }
        return memberHashMap.get(id);
    }

    public void makeAdmin(String userId) {
        getMember(userId).setRole(Role.ADMIN);
    }


}
