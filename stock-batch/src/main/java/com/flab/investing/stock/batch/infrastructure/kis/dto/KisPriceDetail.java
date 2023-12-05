package com.flab.investing.stock.batch.infrastructure.kis.dto;

public record KisPriceDetail(
        String iscd_stat_cls_code,
        String marg_rate,
        String rprs_mrkt_kor_name,
        String bstp_kor_isnm,
        String temp_stop_yn,
        String oprc_rang_cont_yn,
        String clpr_rang_cont_yn,
        String crdt_able_yn,
        String grmn_rate_cls_code,
        String elw_pblc_yn,
        String stck_prpr,
        String prdy_vrss,
        String prdy_vrss_sign,
        String prdy_ctrt,
        String acml_tr_pbmn,
        String acml_vol,
        String prdy_vrss_vol_rate,
        String stck_oprc,
        String stck_hgpr,
        String stck_lwpr,
        String stck_mxpr,
        String stck_llam,
        String stck_sdpr,
        String wghn_avrg_stck_prc,
        String hts_frgn_ehrt,
        String frgn_ntby_qty,
        String pgtr_ntby_qty,
        String pvt_scnd_dmrs_prc,
        String pvt_frst_dmrs_prc,
        String pvt_pont_val,
        String pvt_frst_dmsp_prc,
        String pvt_scnd_dmsp_prc,
        String dmrs_val,
        String dmsp_val,
        String cpfn,
        String rstc_wdth_prc,
        String stck_fcam,
        String stck_sspr,
        String aspr_unit,
        String hts_deal_qty_unit_val,
        String lstn_stcn,
        String hts_avls,
        String per,
        String pbr,
        String stac_month,
        String vol_tnrt,
        String eps,
        String bps,
        String d250_hgpr,
        String d250_hgpr_date,
        String d250_hgpr_vrss_prpr_rate,
        String d250_lwpr,
        String d250_lwpr_date,
        String d250_lwpr_vrss_prpr_rate,
        String stck_dryy_hgpr,
        String dryy_hgpr_vrss_prpr_rate,
        String dryy_hgpr_date,
        String stck_dryy_lwpr,
        String dryy_lwpr_vrss_prpr_rate,
        String dryy_lwpr_date,
        String w52_hgpr,
        String w52_hgpr_vrss_prpr_ctrt,
        String w52_hgpr_date,
        String w52_lwpr,
        String w52_lwpr_vrss_prpr_ctrt,
        String w52_lwpr_date,
        String whol_loan_rmnd_rate,
        String ssts_yn,
        String stck_shrn_iscd,
        String fcam_cnnm,
        String cpfn_cnnm,
        String frgn_hldn_qty,
        String vi_cls_code,
        String ovtm_vi_cls_code,
        String last_ssts_cntg_qty,
        String invt_caful_yn,
        String mrkt_warn_cls_code,
        String short_over_yn,
        String sltr_yn) {
}
