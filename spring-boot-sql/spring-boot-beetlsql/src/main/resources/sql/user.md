findAll
===
* 根据用户名查找
  SELECT * FROM users

findByUserCode
===
* 根据用户名查找
  SELECT * FROM users WHERE true
  -- @if(!isEmpty(userCode)){
  AND user_code = #{userCode}
  -- @}
  LIMIT 1