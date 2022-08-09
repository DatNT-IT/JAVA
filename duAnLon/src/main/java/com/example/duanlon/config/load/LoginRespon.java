package com.example.duanlon.config.load;

import com.example.duanlon.core.Rank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRespon {
    private String username;
    private String password;

    private Rank rank;
}
