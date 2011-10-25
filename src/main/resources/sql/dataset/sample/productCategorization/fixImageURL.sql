DROP PROCEDURE IF EXISTS cleanImageURL;
DELIMITER $$
CREATE PROCEDURE cleanImageURL()
BEGIN

DECLARE done INT DEFAULT 0;
DECLARE i, idx INT;
DECLARE image, imageClean VARCHAR(1000);
DECLARE cur CURSOR FOR SELECT id, imageSrc from product;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

OPEN cur;
read_loop: LOOP 
FETCH cur INTO i, image;
IF done THEN
      LEAVE read_loop;
END IF;
SET imageClean = image;

SET idx = LOCATE('src=', imageClean);
IF  idx > 0 THEN 
	SET imageClean = SUBSTRING(imageClean, idx + 5);
END IF;
SET idx = LOCATE('"', imageClean);
IF  idx > 0 THEN 
	SET imageClean = SUBSTRING(imageClean, 1, idx - 1);
END IF;
IF STRCMP('/', SUBSTRING(imageClean,1,1)) = 0 THEN
	SET imageClean = CONCAT('http://www.kaboodle.com', imageClean);
END IF;
IF STRCMP('http', SUBSTRING(imageClean, 1,4)) != 0 THEN
	SET imageClean = NULL;
END IF;
update product set imageURL = imageClean where id = i;
END LOOP;
CLOSE cur;
END $$
DELIMITER ;$$

call cleanImageURL();