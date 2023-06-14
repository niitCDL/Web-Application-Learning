package top.example.brandlist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    /**
     * 公司名
     */
    private String companyName;
    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 描述
     */
    private String description;

}
