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
@Table(name = "COM_FIN_YEARS")
public class FinYears {
    @Column(name="CFY_SYMBOL_ID")
    private Long cfy_symbol_id;
    @Column(name="CFY_FIN_FROM_DT")
    private LocalDateTime cfy_fin_from_dt;
    @Column(name="CFY_CREAT_BY")
    private String cfy_creat_by;
    @Column(name="CFY_CREAT_DT")
    private LocalDateTime cfy_creat_dt;
    @Column(name="CFY_FIN_TO_DT")
    private LocalDateTime cfy_fin_to_dt;
    @Column(name="CFY_LST_UPD_BY")
    private String cfy_lst_upd_bt;
    @Column(name="CFY_LST_UPD_DT")
    private LocalDateTime cfy_lst_upd_dt;
    @Id
    @Column(name="CFY_ID")
    private Long cfy_id;
}
