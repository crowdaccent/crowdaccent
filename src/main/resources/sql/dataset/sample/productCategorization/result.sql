SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `crowdaccent`
--

-- --------------------------------------------------------
--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hit_id` varchar(200) CHARACTER SET utf8,
  `hit_type_id` varchar(200) CHARACTER SET utf8,
  `title` text CHARACTER SET utf8,
  `description` text CHARACTER SET utf8,
  `keywords` varchar(200) CHARACTER SET utf8,
  `reward` varchar(200) CHARACTER SET utf8,
  `creation_time` timestamp,
  `max_assigments` int,
  `auto_approval_delay_in_secs` int,
  `max_assignments` int,
  `life_time_in_secs` int,
  `requester_annotation` varchar(200) CHARACTER SET utf8,
  `expiration_time` timestamp,
  `assignment_id` varchar(200) CHARACTER SET utf8,
  `worker_id` varchar(200) CHARACTER SET utf8,
  `assignment_status` varchar(200) CHARACTER SET utf8,
  `accept_time` timestamp,
  `submit_time` timestamp,
  `auto_approval_time` timestamp,
  `approval_time` timestamp,
  `rejection_time` timestamp,
  `requester_feedback` varchar(200) CHARACTER SET utf8,
  `work_time_in_secs` int,
  `life_time_approval_rate` varchar(200) CHARACTER SET utf8,
  `last_30_days_approval_rate` varchar(200) CHARACTER SET utf8,
  `last_7_days_approval_rate` varchar(200) CHARACTER SET utf8,
  `answers` varchar(200) CHARACTER SET utf8,
  `hit_status` varchar(200) CHARACTER SET utf8,
  `hit_review_status` varchar(200) CHARACTER SET utf8,
  `num_assignments_pending` varchar(200) CHARACTER SET utf8,
  `num_assignments_available` varchar(200) CHARACTER SET utf8,
  `num_assignments_completed` varchar(200) CHARACTER SET utf8,
  INDEX (hit_id),
  INDEX (hit_type_id),
  INDEX (accept_time),
  INDEX (submit_time),
  INDEX (hit_status),
  -- FOREIGN KEY (`hit_id`) REFERENCES product(hit_id), -- Uncomment me when on adding hit_id to product table the result table creation failure is fixed 
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Result Table' AUTO_INCREMENT=12035 ;
