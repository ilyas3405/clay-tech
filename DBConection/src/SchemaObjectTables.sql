-- Customers Table
CREATE TABLE Customers (
    customer_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15)
);

-- Products Table
CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL DEFAULT 0
);

-- Orders Table
CREATE TABLE Orders (
    order_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);

-- OrderItems Table (Junction table for Orders and Products)
CREATE TABLE OrderItems (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);


INSERT INTO Customers (name, email, phone) VALUES
('John Doe', 'john.doe@example.com', '123-456-7890'),
('Jane Smith', 'jane.smith@example.com', '234-567-8901'),
('Emily Johnson', 'emily.johnson@example.com', '345-678-9012');

INSERT INTO Products (name, price, stock_quantity) VALUES
('Laptop', 999.99, 50),
('Smartphone', 499.99, 100),
('Headphones', 89.99, 200),
('Monitor', 150.00, 75);


INSERT INTO Orders (customer_id, order_date, total_amount) VALUES
(1, '2024-08-01 10:15:00', 1089.98),
(2, '2024-08-02 14:30:00', 649.99),
(1, '2024-08-03 09:45:00', 150.00);


INSERT INTO OrderItems (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 999.99),  -- John Doe's order: 1 Laptop
(1, 3, 1, 89.99),   -- John Doe's order: 1 Headphone
(2, 2, 1, 499.99),  -- Jane Smith's order: 1 Smartphone
(3, 4, 1, 150.00);  -- John Doe's second order: 1 Monitor
