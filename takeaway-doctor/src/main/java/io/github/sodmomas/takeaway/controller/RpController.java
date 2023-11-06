package io.github.sodmomas.takeaway.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.common.enums.RpStatusEnum;
import io.github.sodmomas.takeaway.model.AuthOkRequest;
import io.github.sodmomas.takeaway.model.AuthRejectRequest;
import io.github.sodmomas.takeaway.model.DoctorRpDetail;
import io.github.sodmomas.takeaway.model.DoctorRpListRequest;
import io.github.sodmomas.takeaway.model.entity.Rp;
import io.github.sodmomas.takeaway.service.RpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@RestController
@RequestMapping("/rp")
public class RpController {
    private @Autowired RpService rpService;

    @PostMapping("/page")
    Page<DoctorRpDetail> doctorRpListPage(@RequestBody DoctorRpListRequest request) {
        return rpService.doctorRpListPage(request);
    }

    @GetMapping("/detail/{id}")
    DoctorRpDetail doctorRpDetail(@PathVariable("id") Integer id) {
        return rpService.doctorRpDetail(id);
    }

/**
 * 医生驳回处方
 */
@PostMapping("/doctor/reject")
void doctorReject(@RequestBody AuthRejectRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.DOCTOR_REJECT, request.getRejectReason(), Rp::getDoctorId);
}

/**
 * 医生通过处方
 */
@PostMapping("/doctor/ok")
void doctorOk(@RequestBody AuthOkRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.DOCTOR_OK, "", Rp::getDoctorId);
}

/**
 * 审核药师驳回处方
 */
@PostMapping("/check/reject")
void checkReject(@RequestBody AuthRejectRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.CHECK_PHARMACIST_REJECT, request.getRejectReason(), Rp::getCheckPharmacistId);
}

/**
 * 审核药师通过处方
 */
@PostMapping("/check/ok")
void checkOk(@RequestBody AuthOkRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.CHECK_PHARMACIST_OK, "", Rp::getCheckPharmacistId);
}

/**
 * 复核药师驳回处方
 */
@PostMapping("/recheck/reject")
void recheckReject(@RequestBody AuthRejectRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.RECHECK_PHARMACIST_REJECT, request.getRejectReason(), Rp::getRecheckPharmacistId);
}

/**
 * 复核药师通过处方
 */
@PostMapping("/recheck/ok")
void recheckOk(@RequestBody AuthOkRequest request) {
    rpService.auth(request.getId(), RpStatusEnum.RECHECK_PHARMACIST_OK, "", Rp::getRecheckPharmacistId);
}
}
