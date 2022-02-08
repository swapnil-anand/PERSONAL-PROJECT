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
@Table(name = "TBL_COMPANY_MASTER")
public class CompanyMaster{
    @Id
    @Column(name = "CM_ID")
    private Long cm_id;
    @Column(name = "CM_SYMBOL")
    private String cm_symbol;
    @Column(name = "CM_SYMBOL_ID")
    private Long cm_symbol_id;
    @Column(name = "CM_OLD_SYMBOL")
    private String cm_old_symbol;
    @Column(name = "CM_OLD_SYMBOL_ID")
    private Long cm_base_symbol_id;
    @Column(name = "CM_BASE_SYMBOL")
    private String cm_base_symbol;
    @Column(name = "CM_COMPANY_NAME")
    private String cm_company_name;
    @Column(name = "CM_ISSUER_TYPE")
    private String cm_issuer_type;
    @Column(name = "CM_CG_EFFECTIVE_DATE")
    private LocalDateTime cm_cg_effective_date;
    @Column(name = "CM_SYM_CREATE_MODULE")
    private String cm_sym_create_module;
    @Column(name = "CM_REG_STOCK_EXCHG")
    private String cm_reg_stock_exchg;
    @Column(name = "CM_FIN_FROM_DT")
    private LocalDateTime cm_fin_from_dt;
    @Column(name = "CM_FIN_TO_DATE")
    private LocalDateTime cm_fin_to_date;
    @Column(name = "CM_CREATE_BY")
    private String cm_create_by;
    @Column(name = "CM_CREATE_DT")
    private LocalDateTime cm_create_dt;
    @Column(name = "CM_UPDATE_BY")
    private String cm_update_By;
    @Column(name = "CM_UPDATE_DT")
    private LocalDateTime cm_update_dt;
    @Column(name = "CM_HISTORY")
    private String cm_history;
    @Column(name = "CM_CIN")
    private String cm_cin;
    @Column(name = "CM_CO_AM_ID")
    private String cm_co_am_id;
    @Column(name = "CM_RG_AM_ID")
    private String cm_rg_am_id;
    @Column(name = "CM_CP_ID")
    private String cm_cp_id;
    @Column(name = "CM_STATUS")
    private String cm_status;
    @Column(name = "CM_WEBSITE")
    private String cm_website;
    @Column(name = "CM_COMPANY_TYPE")
    private String cm_company_type;
    @Column(name = "CM_APPROVED_BY")
    private String cm_approved_by;
    @Column(name = "CM_APPROVED_DT")
    private LocalDateTime cm_approved_dt;
    @Column(name = "CM_SCORES_ID")
    private String cm_scores_id;
    @Column(name = "CM_DEBT_SYMBOL")
    private String cm_debt_symbol;
    @Column(name = "CM_IS_DEBT_ELIGIBLE")
    private String cm_is_debt_eligible;
    @Column(name = "CM_ORG_ID")
    private Long cm_org_id;
    @Column(name = "CM_INDUSTRY_DESC")
    private String cm_industry_desc;
    @Column(name = "CM_INC_ACT")
    private String cm_inc_act;
    @Column(name = "CM_INC_DT")
    private LocalDateTime cm_inc_dt;
    @Column(name = "CM_MARK_DELETION")
    private String cm_mark_deletion;
    @Column(name = "CM_LISTING_STATUS")
    private String cm_listing_status;
    @Column(name = "CM_SECURITY_TYPE")
    private String cm_security_type;
    @Column(name = "CM_IS_CM_ELIGIBLE")
    private String cm_is_cm_eligible;
    @Column(name = "CM_EQ_LIST_TYPE")
    private String cm_eq_list_type;
}