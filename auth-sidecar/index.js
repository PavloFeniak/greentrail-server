const express = require('express');
const rateLimit = require('express-rate-limit');
const { createProxyMiddleware } = require('http-proxy-middleware');

const app = express();

// Дозволити читати X-Forwarded-For
app.set('trust proxy', 1);

// Rate limiter
const loginLimiter = rateLimit({
    windowMs: 60 * 1000,
    max: 20,
    message: 'Too many login attempts from this IP, please try again later.',
    standardHeaders: true,
    legacyHeaders: false,
});

// Healthcheck endpoint
app.get('/healthz', (req, res) => res.send('ok'));

// Apply limiter
app.use('/auth-service/**', loginLimiter);

// Проксі на auth-service
app.use(
    '/',
    createProxyMiddleware({
        target: 'http://localhost:8088',
        changeOrigin: true,
    })
);

// Старт сервера
app.listen(3000, () => {
    console.log('Rate-limiting sidecar listening on port 3000');
});
