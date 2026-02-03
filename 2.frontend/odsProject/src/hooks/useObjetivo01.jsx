import { useState, useEffect } from 'react';
import { objetivo01Service } from '../services/objetivo01Service';

export const useObjetivo01 = () => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState({});
  const [error, setError] = useState({});

  const fetchData = async (indicator) => {
    setLoading(prev => ({ ...prev, [indicator]: true }));
    setError(prev => ({ ...prev, [indicator]: null }));
    
    try {
      const methodName = `getIndicador_${indicator}`;
      const result = await objetivo01Service[methodName]();
      setData(prev => ({ ...prev, [indicator]: result }));
    } catch (err) {
      setError(prev => ({ ...prev, [indicator]: err.message }));
    } finally {
      setLoading(prev => ({ ...prev, [indicator]: false }));
    }
  };

  const fetchAllData = async () => {
    const indicators = [
      '1_1_1',
      '1_2_1', 
      '1_2_2',
      '1_3_1',
      '1_4_1',
      '1_4_2',
      '1_5_1',
      '1_5_2',
      '1_5_3',
      '1_5_4',
      '1_a_1',
      '1_a_2',
      '1_b_1'
    ];

    await Promise.all(indicators.map(indicator => fetchData(indicator)));
  };

  return {
    data,
    loading,
    error,
    fetchData,
    fetchAllData
  };
};
