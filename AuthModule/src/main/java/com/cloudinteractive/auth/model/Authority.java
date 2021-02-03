package com.cloudinteractive.auth.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Authority {
    private AuthorityName name;

    public Authority(AuthorityName name){
        this.name = name;
    }
}