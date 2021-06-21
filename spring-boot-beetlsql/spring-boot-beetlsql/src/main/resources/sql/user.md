findAll
===
* 根据用户名查找
  SELECT * FROM user

findByUserCode
===
* 根据用户名查找
  SELECT * FROM user WHERE true
  -- @if(!isEmpty(userCode)){
  AND user_code = #{userCode}
  -- @}
  LIMIT 1