--
-- Table structure for table `brands_lc`
--

CREATE TABLE brands_lc (
  brand_id varchar2(100) NOT NULL,
  order_num number(10) DEFAULT 0 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `crosses`
--

CREATE TABLE crosses (
  id number(10) NOT NULL,
  art_numbers clob NOT NULL,
  vendor_id number(10) NOT NULL,
  import_group_id varchar2(32) DEFAULT 1 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `crosses_search`
--

CREATE TABLE crosses_search (
  art_number_clear varchar2(100) NOT NULL,
  line_id number(10) NOT NULL,
  vendor_id number(10) NOT NULL,
  import_group_id varchar2(32) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `delivery_methods`
--

CREATE TABLE delivery_methods (
  id number(10) NOT NULL,
  title varchar2(255) NOT NULL,
  descr clob,
  price number(10) NOT NULL,
  is_available number(10) DEFAULT 1 NOT NULL,
  order_num number(10) DEFAULT 0 NOT NULL,
  is_default number(10) DEFAULT 0 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE options (
  id number(10) NOT NULL,
  option_name varchar2(64) NOT NULL,
  option_value clob NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE orders (
  id number(10) NOT NULL,
  order_human_id number(10) DEFAULT 0 NOT NULL,
  user_id number(10) NOT NULL,
  vericode varchar2(40) NOT NULL,
  order_comment varchar2(255) DEFAULT NULL,
  amount_paid binary_double DEFAULT '0.00' NOT NULL,
  date_ number(10) NOT NULL,
  order_status number(10) DEFAULT NULL,
  order_status_changed number(10) DEFAULT NULL,
  opened_by number(10) DEFAULT NULL,
  closed_by number(10) DEFAULT NULL,
  is_archived number(3) DEFAULT 0 NOT NULL,
  is_sync_inprogress number(3) DEFAULT 0 NOT NULL,
  is_synched number(3) DEFAULT 0 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE order_items (
  id number(10) NOT NULL,
  cartid varchar2(40) DEFAULT NULL,
  orderid number(10) DEFAULT NULL,
  line_hash varchar2(32) DEFAULT NULL,
  prices_line_id number(10) DEFAULT NULL,
  art_number varchar2(100) DEFAULT NULL,
  sup_brand varchar2(255) DEFAULT NULL,
  description varchar2(255) DEFAULT NULL,
  price binary_double DEFAULT 0.00 NOT NULL,
  qty binary_double DEFAULT 0.00 NOT NULL,
  qty_limit number(10) DEFAULT 0 NOT NULL,
  qty_lot_size number(10) DEFAULT 1 NOT NULL,
  discount number(10) DEFAULT 0 NOT NULL,
  vendor_id number(10) DEFAULT NULL,
  vendor_name varchar2(255) DEFAULT NULL,
  delivery_days number(10) DEFAULT NULL,
  delivery_method number(10) DEFAULT NULL,
  is_updated_by_user number(3) DEFAULT 0 NOT NULL,
  status number(10) DEFAULT 1 NOT NULL,
  status_change_date number(10) DEFAULT NULL,
  type_item VARCHAR2(10) DEFAULT 'item' NOT NULL check (type_item in ('item','service')) 
) ;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE posts (
  id number(10) NOT NULL,
  title varchar2(255) NOT NULL,
  permalink varchar2(100) NOT NULL,
  thumbnail varchar2(255) DEFAULT NULL,
  text clob,
  meta clob,
  meta2 clob,
  last_update number(10) NOT NULL,
  allow_delete number(3) DEFAULT 1 NOT NULL,
  is_in_menu number(3) DEFAULT 1 NOT NULL,
  menu_order number(10) DEFAULT 0 NOT NULL,
  term_id number(10) DEFAULT NULL,
  post_type varchar2(255) DEFAULT 'page' NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `post_terms`
--

CREATE TABLE post_terms (
  id number(10) NOT NULL,
  title varchar2(255) NOT NULL,
  permalink varchar2(100) NOT NULL,
  image varchar2(255) DEFAULT NULL,
  parent_id number(10) DEFAULT 0 NOT NULL,
  order_num number(10) DEFAULT 0 NOT NULL,
  term_type varchar2(255) DEFAULT 'category' NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `prices`
--

CREATE TABLE prices (
  id number(19) NOT NULL,
  art_number varchar2(100) NOT NULL,
  art_number_clear varchar2(100) NOT NULL,
  sup_brand varchar2(255) NOT NULL,
  description varchar2(255) NOT NULL,
  qty number(10) NOT NULL,
  price binary_double NOT NULL,
  vendor_id number(10) DEFAULT 1 NOT NULL,
  import_group_id varchar2(32) DEFAULT 1 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

CREATE TABLE sessions (
  session_id varchar2(40) DEFAULT 0 NOT NULL,
  ip_address varchar2(45) DEFAULT 0 NOT NULL,
  user_agent varchar2(120) NOT NULL,
  last_activity number(10) DEFAULT 0 CHECK (last_activity > 0) NOT NULL,
  user_data clob NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `sites`
--

CREATE TABLE sites (
  title varchar2(100) NOT NULL,
  subtitle varchar2(100) NOT NULL,
  adminpass varchar2(32) NOT NULL,
  adminemail varchar2(100) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `stats_search`
--

CREATE TABLE stats_search (
  id number(19) NOT NULL,
  q varchar2(255) NOT NULL,
  timestamp_ number(10) NOT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE users (
  id number(10) NOT NULL,
  email varchar2(255) NOT NULL,
  password_ varchar2(255) NOT NULL,
  vericode varchar2(64) NOT NULL,
  discount number(10) DEFAULT 0 NOT NULL,
  userdata clob NOT NULL,
  is_sample number(3) DEFAULT 0 NOT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `vendors`
--

CREATE TABLE vendors (
  id number(10) NOT NULL,
  vendor_name varchar2(255) NOT NULL,
  vendor_type enum('default','crosses') DEFAULT 'default' NOT NULL,
  delivery_days number(10) DEFAULT 0 NOT NULL,
  price_correction binary_double DEFAULT '1.00' NOT NULL,
  structure_id number(5) DEFAULT 1 NOT NULL,
  struct_art_number number(3) DEFAULT 1 CHECK (struct_art_number > 0) NOT NULL,
  struct_sup_brand number(3) DEFAULT 1 CHECK (struct_sup_brand > 0) NOT NULL,
  struct_description number(3) DEFAULT 3 CHECK (struct_description > 0) NOT NULL,
  struct_qty number(3) DEFAULT 4 CHECK (struct_qty > 0) NOT NULL,
  struct_price number(3) DEFAULT 5 CHECK (struct_price > 0) NOT NULL,
  last_update number(10) DEFAULT 0 NOT NULL,
  rows_cache number(10) DEFAULT 0 NOT NULL,
  qtys_cache number(10) DEFAULT 0 NOT NULL,
  import_group_id varchar2(32) DEFAULT 1 NOT NULL,
  allow_delete number(3) DEFAULT 1 NOT NULL,
  is_primary number(3) DEFAULT 0 NOT NULL,
  api_id varchar2(20) DEFAULT NULL,
  api_key1 varchar2(255) DEFAULT NULL,
  api_key2 varchar2(255) DEFAULT NULL,
  ordername varchar2(128) DEFAULT NULL,
  orderemail varchar2(128) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Table structure for table `vendor_apipull_cache`
--

CREATE TABLE vendor_apipull_cache (
  hash_ varchar2(32) NOT NULL,
  data_ clob NOT NULL,
  time_ number(10) NOT NULL,
  siteid varchar2(100) NOT NULL
) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `crosses`
--
ALTER TABLE crosses
  ADD PRIMARY KEY (id);

--
-- Indexes for table `crosses_search`
--
ALTER TABLE crosses_search
  ADD CREATE INDEX art_number_clear ON crosses_search (art_number_clear);

--
-- Indexes for table `delivery_methods`
--
ALTER TABLE delivery_methods
  ADD PRIMARY KEY (id);

--
-- Indexes for table `options`
--
ALTER TABLE options
  ADD PRIMARY KEY (id);

--
-- Indexes for table `orders`
--
ALTER TABLE orders
  ADD PRIMARY KEY (id);

--
-- Indexes for table `order_items`
--
ALTER TABLE order_items
  ADD PRIMARY KEY (id),
  ADD KEY `orderid` (orderid);

--
-- Indexes for table `posts`
--
ALTER TABLE posts
  ADD PRIMARY KEY (id);

--
-- Indexes for table `post_terms`
--
ALTER TABLE post_terms
  ADD PRIMARY KEY (id);

--
-- Indexes for table `prices`
--
ALTER TABLE prices
  ADD PRIMARY KEY (id),
  ADD KEY `art_number_clear` (art_number_clear),
  ADD KEY `sup_brand` (sup_brand),
  ADD KEY `vendor_id` (vendor_id);

--
-- Indexes for table `sessions`
--
ALTER TABLE sessions
  ADD PRIMARY KEY (session_id),
  ADD KEY `last_activity_idx` (last_activity);

--
-- Indexes for table `stats_search`
--
ALTER TABLE stats_search
  ADD PRIMARY KEY (id);

--
-- Indexes for table `users`
--
ALTER TABLE users
  ADD PRIMARY KEY (id);

--
-- Indexes for table `vendors`
--
ALTER TABLE vendors
  ADD PRIMARY KEY (id);

--
-- Indexes for table `vendor_apipull_cache`
--
ALTER TABLE vendor_apipull_cache
  ADD CONSTRAINT hash UNIQUE  (hash);

--
-- AUTO_INCREMENT for table `crosses`
--
ALTER TABLE crosses
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `delivery_methods`
--
ALTER TABLE delivery_methods
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE options
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE orders
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE order_items
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE posts
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `post_terms`
--
ALTER TABLE post_terms
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `prices`
--
ALTER TABLE prices
MODIFY id trunc(to_number(24)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `stats_search`
--
ALTER TABLE stats_search
MODIFY id trunc(to_number(20)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE users
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT for table `vendors`
--
ALTER TABLE vendors
MODIFY id trunc(to_number(11)) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;