package cn.jason.tool.test;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息表
 *
 * @author bigsail
 * @email zhli_1985@163.com
 * @date 2019-10-09 17:39:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 准考证号
     */
    private String examNo;
    /**
     * 学籍号
     */
    private String sid;
    /**
     * 序列号
     */
    private String serialNo;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 证件类型
     */
    private String idType;
    /**
     * 证件号码
     */
    private String idNo;
    /**
     * 出生日期
     */
    private Date birthday;
    /**
     * 考生照片路径
     */
    private String picUrl;
    /**
     * 民族
     */
    private String nation;
    /**
     * 籍贯
     */
    private String nativePlace;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 政治面貌
     */
    private String politicalType;
    /**
     * 户口类型
     */
    private String householdType;
    /**
     * 户口所在县区
     */
    private String householdCounty;
    /**
     * 家庭住址
     */
    private String householdAddress;
    /**
     * 现住址
     */
    private String nowAddress;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 报考类别
     */
    private String examCategory;
    /**
     * 选考科目
     */
    private String electiveSubject;
    /**
     * 毕业中学
     */
    private String graduateSchool;
    /**
     * 毕业学校所属县区
     */
    private String graduateSchoolCounty;
    /**
     * 年级
     */
    private String grade;
    /**
     * 毕业班级
     */
    private String graduateClass;
    /**
     * 考生类别
     */
    private String candidateCategory;
    /**
     * 小学就读年份
     */
    private String primarySchoolStudyYear;
    /**
     * 初中就读年份
     */
    private String middleSchoolStudyYear;
    /**
     * 特长
     */
    private String specialSkill;
    /**
     * 将惩情况
     */
    private String rewardsAndPenalties;
    /**
     * 考生状态
     */
    private String status;
    /**
     * 学籍状态
     */
    private String schoolRollStatus;
    /**
     * 总志愿资格
     */
    private String mainQualifications;
    /**
     * 分项志愿资格（是否省属高中录取|自主招生|指标到校|择优录取|征集志愿|民办志愿）
     */
    private String subQualifications;
    /**
     * 是否辖区内（原三区内/外）
     */
    private String inServiceArea;
    /**
     * 考生去向
     */
    private String toPlace;
    /**
     * 备注
     */
    private String remark;
    /**
     * 择优志愿划批次ID
     */
    private String batchId;
    /**
     * 录取编号
     */
    private String enrollNo;
    /**
     * 录取类型
     */
    private String enrollType;
    /**
     * 录取学校
     */
    private String enrollSchool;
    /**
     * 使用城市农村计划数
     */
    private String useTownRuralPlan;
    /**
     * 密码
     */
    private String password;
    /**
     * 密码的盐
     */
    private String salt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
