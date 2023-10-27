package io.github.sodmomas.takeaway;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.sodmomas.takeaway.model.entity.DrugEntity;
import io.github.sodmomas.takeaway.mapper.DrugMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.sql.*;
import java.util.Calendar;
import java.util.List;


@SpringBootTest
    class TakeawayApplicationTests {
    @Autowired
    private DrugMapper drugMapper;

    @Test
    void testSqlConnect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        final String jdbc = "jdbc:postgresql://localhost:5432/takeaway";
        final Connection connection = DriverManager.getConnection(jdbc, "io/github/sodmomas/takeaway", "io/github/sodmomas/takeaway");
        final PreparedStatement ps = connection.prepareStatement("SELECT 1");
        final ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            final int result = rs.getInt(1);
            System.out.println(result);
        }
        rs.close();
        ps.close();
        connection.close();
    }

    @Test
    void addDrug() {
        DrugEntity drug = new DrugEntity();
        drug.setApprovalNumber("国药准字H23023610");
        drug.setProductName("阿司匹林维C肠溶片");
        drug.setEnglishName("Asipilin Vitamin C Enteric-coated Tablets");
        drug.setBrandName("");
        drug.setDosageForm("片剂（肠溶）");
        drug.setSpecification("阿司匹林0.25克，维生素C25毫克");
        drug.setLicenseHolder("黑龙江华玺生物医药科技有限公司");
        drug.setLicenseHolderAddress("黑龙江省鸡西市虎林市经济开发区管理委员会三楼东侧301室");
        drug.setManufacturer("黑龙江大众安泰药业有限公司");
        final Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2023);
        instance.set(Calendar.MONTH, 2);
        instance.set(Calendar.DAY_OF_MONTH, 24);
        drug.setApprovalDate(instance.getTime());
        drug.setManufacturingAddress("黑龙江省鸡西市密山市连珠山镇星光工业园区");
        drug.setProductCategory("化学药品");
        drug.setOriginalApprovalNumber("国药准字H23023610");
        drug.setDrugStandardCode("86903694000021");
        drug.setDrugStandardCodeRemark("");

        final int success = drugMapper.insert(drug);
        System.out.println(success);
    }

    @Test
    void listDrug() {
        final List<DrugEntity> drugs = drugMapper.selectList(Wrappers.emptyWrapper());
        System.out.println(drugs);
    }
    @Test
    void redisTest() {
        RedisStandaloneConfiguration cfg = new RedisStandaloneConfiguration();
        cfg.setPassword("123456");
        LettuceConnectionFactory fac = new LettuceConnectionFactory(cfg);
        fac.afterPropertiesSet();
        StringRedisTemplate client = new StringRedisTemplate(fac);
        if (Boolean.FALSE.equals(client.hasKey("test"))) {
            client.opsForValue().set("test", "from test unit");
        }
        String value = client.opsForValue().get("test");
        System.out.println("key:test value:" + value);
        client.delete("test");
    }
}
