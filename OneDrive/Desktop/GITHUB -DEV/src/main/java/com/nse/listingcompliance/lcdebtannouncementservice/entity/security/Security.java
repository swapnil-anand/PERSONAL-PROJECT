package com.nse.listingcompliance.lcdebtannouncementservice.entity.security;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(Security.class)
@Table(name = "SECURITIES")
public class Security implements Serializable {
    @Id
    @Column(name = "SEC_SYMBOL")
    private String sec_symbol;
    @Id
    @Column(name = "SEC_SERIES")
    private String sec_series;
    @Column(name = "SEC_SNAME")
    private String sec_sname;
    @Column(name = "SEC_LNAME")
    private String sec_lname;
    @Column(name = "SEC_INSTR_TYPE")
    private Boolean sec_instr_type;
    @Column(name = "SEC_ISIN_CD")
    private String sec_isin_cd;
    @Column(name = "SEC_ISS_CAP")
    private Long sec_iss_cap;
    @Column(name = "SEC_FACE_VAL")
    private double sec_face_val;
    @Column(name = "SEC_MKT_LOT")
    private Long sec_mkt_lot;
    @Column(name = "SEC_ISS_PR")
    private double sec_iss_pr;
    @Column(name = "SEC_STRT_DT")
    private LocalDateTime sec_strt_dt;
    @Column(name = "SEC_IP_DT")
    private LocalDateTime sec_ip_dt;
    @Column(name = "SEC_MAT_DT")
    private LocalDateTime sec_mat_dt;
    @Column(name = "SEC_INT_RT")
    private double sec_int_rt;
    @Column(name = "SEC_ADM_TYPE")
    private String sec_adm_type;
    @Column(name = "SEC_ADM_DT")
    private LocalDateTime sec_adm_dt;
    @Column(name = "SEC_READM_DT")
    private LocalDateTime sec_readm_dt;
    @Column(name = "SEC_EXPL_DT")
    private LocalDateTime sec_expl_dt;
    @Column(name = "SEC_DNLD_FLG")
    private String sec_dnld_flg;
    @Column(name = "SEC_MKT_FLG")
    private String sec_mkt_flg;
    @Column(name = "SEC_STATUS")
    private String sec_status;
    @Column(name = "SEC_PDUP_PR")
    private double sec_pdup_pr;
    @Column(name = "SEC_LIST_DT")
    private LocalDateTime sec_list_dt;
    @Column(name = "SEC_CREDIT_RATING")
    private String sec_credit_rating;
    @Column(name = "SEC_REMARKS")
    private String sec_remarks;
    @Column(name = "SEC_CREAT_BY")
    private String sec_creat_by;
    @Column(name = "SEC_CREAT_DT")
    private LocalDateTime sec_creat_dt;
    @Column(name = "SEC_LST_UPD_BY")
    private String sec_lst_upd_by;
    @Column(name = "SEC_LST_UPD_DT")
    private LocalDateTime sec_lst_upd_dt;
    @Column(name = "SEC_SUSP_FLG")
    private String sec_susp_flg;
    @Column(name = "SEC_SUSP_DT")
    private LocalDateTime sec_susp_dt;
    @Column(name = "SEC_TEMP_SUSP_FROM")
    private String sec_temp_susp_from;
    @Column(name = "SEC_TEMP_RESTART_DT")
    private LocalDateTime sec_temp_restart_dt;
    @Column(name = "SEC_LIST_ISS_SIZE")
    private Long sec_list_iss_size;
    @Column(name = "SEC_GOVT_HOLD")
    private double sec_govt_hold;
    @Column(name = "SEC_SERIES_FLG")
    private String sec_series_flg;
    @Column(name = "SEC_TEMP_SUSP_FLG")
    private String sec_temp_susp_flg;
    @Column(name = "SEC_COMP_DEMAT")
    private String sec_comp_demat;
    @Column(name = "SEC_COMP_DEMAT_DT")
    private LocalDateTime sec_comp_demat_dt;
    @Column(name = "SEC_IDFN_FLG")
    private String sec_idfn_flg;
    @Column(name = "SEC_INSTRM_TYPE")
    private Long sec_instrm_type;
    @Column(name = "SEC_INSTRM_SUBTYPE")
    private Long sec_instrm_subtype;

    @ManyToOne
    @JoinColumn(name = "SEC_SYMBOL_ID")
    private Symbol symbol;

    @Column(name = "SEC_REASON")
    private String sec_reason;
    @Column(name = "SEC_INTEREST_TYPE")
    private String sec_interest_type;
    @Column(name = "SEC_RELISTING_TYPE")
    private String sec_relisting_type;

}

