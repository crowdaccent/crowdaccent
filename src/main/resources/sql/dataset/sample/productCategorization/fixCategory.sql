DROP PROCEDURE IF EXISTS cleanCategory;
DELIMITER $$
CREATE PROCEDURE cleanCategory()
BEGIN

DECLARE done INT DEFAULT 0;
DECLARE i, idx INT;
DECLARE cat, catClean VARCHAR(1000);
DECLARE cur CURSOR FOR SELECT id, CONVERT(attributes USING utf8) from product;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

OPEN cur;
read_loop: LOOP 
FETCH cur INTO i, cat;
IF done THEN
      LEAVE read_loop;
END IF;
SET catClean = cat;

SET idx = LOCATE('ation=', catClean);
IF  idx > 0 THEN 
	SET catClean = SUBSTRING(catClean, idx + 6);
	ELSE
	SET catClean = NULL;
END IF;
SET idx = LOCATE('$', catClean);
IF  idx > 0 THEN 
	SET catClean = SUBSTRING(catClean, 1, idx - 1);
END IF;
IF LENGTH(catClean) = 0 THEN
	SET catClean = NULL;
END IF;

update product set Category = catClean where id = i;
END LOOP;
CLOSE cur;
END $$
DELIMITER ;$$

call cleanCategory();