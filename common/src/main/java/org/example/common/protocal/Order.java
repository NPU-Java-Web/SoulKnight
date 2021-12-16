package org.example.common.protocal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 命令类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    String playerId;
    String command;

}
