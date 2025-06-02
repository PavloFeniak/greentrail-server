local limit_req = require "resty.limit.req"

local prometheus = _G.prometheus
local metric_requests = _G.auth_requests_total

if not prometheus or not metric_requests then
    ngx.log(ngx.ERR, "prometheus or metric_requests is not initialized")
    return ngx.exit(500)
end

local lim, err = limit_req.new("limits", 5, 10)
if not lim then
    ngx.log(ngx.ERR, "failed to instantiate limiter: ", err)
    return ngx.exit(500)
end

local key = ngx.var.remote_addr
local delay, err = lim:incoming(key, true)
if not delay then
    metric_requests:inc(1, {key, "blocked"})
    return ngx.exit(429)
end

metric_requests:inc(1, {key, "allowed"})
