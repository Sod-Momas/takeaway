package io.github.sodmomas.takeaway.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Sod-Momas
 * @since 2023/11/5
 */
@Data
@Schema(description = "登录请求")
public class LoginQuery {
    @NotBlank(message = "用户名为空")
    @Schema(description = "用户名")
    private String username;
    @NotBlank(message = "密码为空")
    @Schema(description = "密码")
    private String password;
}
