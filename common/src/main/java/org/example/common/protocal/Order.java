package org.example.common.protocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 命令实体类
 *
 * @author 廖菁璞
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /**
     * 发起命令的玩家ID
     */
    String playerId;
    /**
     * 命令的内容
     */
    String command;

}
