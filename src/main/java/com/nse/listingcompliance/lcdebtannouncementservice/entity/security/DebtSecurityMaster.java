package com.nse.listingcompliance.lcdebtannouncementservice.entity.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TBL_DEBT_SECURITY_MAS")
public class DebtSecurityMaster{
    @Id
    @Column(name="DSM_ID")
    private Long dsm_id;
    @Column(name="DSM_CM_ID")
    private Long dsm_cm_id;
    @Column(name="DSM_ISSUER_NAME")
    private String dsm_issuer_name;
    @Column(name="DSM_ISSUER_STATUS")
    private String dsm_issuer_status;
    @Column(name="DSM_ISSUER_CODE")
    private String dsm_issuer_code;
    @Column(name="DSM_FINAL_LISTING_REF_APPNO")
    private Long dsm_final_listing_ref_appno;
    @Column(name="DSM_FINAL_LISTING_REF_APPDT")
    private LocalDateTime dsm_final_listing_red_appdt;
    @Column(name="DSM_INPRINCIPLE_REF_APPNO")
    private Long dsm_inprinciple_ref_appno;
    @Column(name="DSM_INPRINCIPLE_REF_APPDT")
    private LocalDateTime dsm_inprinciple_ref_appdt;
    @Column(name="DSM_ISSUE_TYPE")
    private String dsm_issue_type;
    @Column(name="DSM_SETTLEMENT_DAY")
    private LocalDateTime dsm_settlement_day;
    @Column(name="DSM_STOCK_SECURITY_NAME")
    private String dsm_stock_security_name;
    @Column(name="DSM_SECURITY_NAME")
    private String dsm_security_name;
    @Column(name="DSM_ISIN_CODE")
    private String dsm_isin_code;
    @Column(name="DSM_ALLOTMENT_DT")
    private LocalDateTime dsm_allotment_dt;
    @Column(name="DSM_MATURITY_DT")
    private LocalDateTime dsm_maturity_dt;
    @Column(name="DSM_ISSUE_DESCRIPTION")
    private String dsm_issue_description;
    @Column(name="DSM_INSTRM_TYPE")
    private Long dsm_instrm_type;
    @Column(name="DSM_INSTRM_SUBTYPE")
    private Long dsm_instrm_subtype;
    @Column(name="DSM_MARKET_LOT")
    private Long dsm_market_lot;
    @Column(name="DSM_BASE_PRICE")
    private Long dsm_base_price;
    @Column(name="DSM_SECURITY_CHARGE")
    private String dsm_security_charge;
    @Column(name="DSM_TAX_STATUS")
    private String dsm_tax_status;
    @Column(name="DSM_FACE_VAL")
    private Long dsm_face_val;
    @Column(name="DSM_PAIDUP_VAL")
    private Long dsm_paidup_val;
    @Column(name="DSM_ISSUE_SIZE")
    private Long dsm_issue_size;
    @Column(name="DSM_LIST_ISSUE_SIZE")
    private Long dsm_list_issue_size;
    @Column(name="DSM_ISSUE_PRICE")
    private Long dsm_issue_price;
    @Column(name="DSM_TOT_NO_OF_BONDS_OFFER")
    private Long dsm_tot_no_of_bonds_offer;
    @Column(name="DSM_TOT_NO_OF_BONDS_GREENSHOE")
    private Long dsm_tot_no_of_bonds_greenshoe;
    @Column(name="DSM_TOT_NO_OF_BONDS")
    private Long dsm_tot_no_of_bonds;
    @Column(name="DSM_LOCKIN_UPTO_FROM")
    private LocalDateTime dsm_lockin_upto_from;
    @Column(name="DSM_LOCKIN_UPTO_TO")
    private LocalDateTime dsm_lockin_upto_to;
    @Column(name="DSM_COMPUL_DEMAT_ROLLING_STAT")
    private String dsm_compul_demat_rolling_stat;
    @Column(name="DSM_COMPUL_DEMAT_ROLLING_DT")
    private LocalDateTime dsm_compul_demat_rolling_dt;
    @Column(name="DSM_STATUS")
    private String dsm_status;
    @Column(name="DSM_ADMISSION_STATUS")
    private String dsm_admission_status;
    @Column(name="DSM_ADMISSION_DT")
    private LocalDateTime dsm_admission_dt;
    @Column(name="DSM_TRADE_STRT_DT")
    private LocalDateTime dsm_trade_strt_dt;
    @Column(name="DSM_PERM_SUSP_FROM")
    private LocalDateTime dsm_perm_susp_from;
    @Column(name="DSM_PERM_SUSP_FLG")
    private String dsm_perm_susp_flg;
    @Column(name="DSM_TEMP_SUSP_FROM")
    private LocalDateTime dsm_temp_susp_from;
    @Column(name="DSM_TEMP_SUSP_FLG")
    private String dsm_temp_susp_flg;
    @Column(name="DSM_RESTART_DT")
    private LocalDateTime dsm_restart_dt;
    @Column(name="DSM_TOTAL_TARGETED_AMT")
    private double dsm_total_targeted_amt;
    @Column(name="DSM_REMARKS")
    private String dsm_remarks;
    @Column(name="DSM_CREAT_BY")
    private String dsm_creat_by;
    @Column(name="DSM_CREAT_DT")
    private LocalDateTime dsm_creat_dt;
    @Column(name="DSM_LST_UPD_BY")
    private String dsm_lst_upd_by;
    @Column(name="DSM_LST_UPD_DT")
    private LocalDateTime dsm_lst_upd_dt;
    @Column(name="DSM_DELETED_BY")
    private String dsm_deleted_by;
}