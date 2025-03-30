

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    address TEXT NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('ADMIN', 'MEMBER'))
);

-- Members Table
CREATE TABLE IF NOT EXISTS members (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
    profile_picture TEXT,
    address TEXT,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP,

    CONSTRAINT fk_members_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Admin Table
CREATE TABLE IF NOT EXISTS admin (
    id SERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
    city VARCHAR(100),
    state VARCHAR(100),
    country VARCHAR(100),
    profile_image_url TEXT,
    designation VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED')),

    CONSTRAINT fk_admin_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
