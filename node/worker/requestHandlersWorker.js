function noWork(client, data) {
    console.log("noWork");
}

function workReady(client, data) {
}

function submitWorkload(client, data) {
}

function jobStatus(client, data) {
}

function workloadStatusReq(client, data) {
}

module.exports.noWork = noWork;
module.exports.workReady = workReady;
module.exports.submitWorkload = submitWorkload;
module.exports.jobStatus = jobStatus;
module.exports.workloadStatusReq = workloadStatusReq;

//module.exports.work_available = work_available;
//module.exports.workload_received = workload_received;
//module.exports.job_status = job_status;
//module.exports.workload_status_req = workload_status_req;
//module.exports.workload_status = workload_status;
//module.exports.workload_status_complete = workload_status_complete;
//module.exports.workload_status_fail = workload_status_fail;
//module.exports.echo_request = echo_request;
//module.exports.echo_response = echo_response;
//module.exports.error = error;
//module.exports.worker_specs_req = worker_specs_req;
//module.exports.worker_specs_res = worker_specs_res;
//module.exports.set_worker_opts = set_worker_opts;
//module.exports.get_worker_opts = get_worker_opts;
//module.exports.set_group = set_group;
//module.exports.get_group = get_group;
//module.exports.worker_conec_req = worker_conec_req;
